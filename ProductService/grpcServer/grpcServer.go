package grpcserver

import (
	"log"
	"log/slog"
	"net"

	pb "github.com/ridhwan90/MSA-ecommerce/ProductService/productServicePb/proto"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/repository"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/services"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/utils"
	"go.mongodb.org/mongo-driver/mongo"
	"google.golang.org/grpc"
	"google.golang.org/grpc/reflection"
)

func InitializeGrpcServer(config *utils.Configuration, dbClient *mongo.Client) {
	listner, err := net.Listen("tcp", ":"+config.Server.Port)
	slog.Info("GrpcServer is running on port " + config.Server.Port)

	if err != nil {
		log.Fatal(err)
	}

	serverRegistration := grpc.NewServer()

	if err != nil {
		log.Fatal(err)
	}

	productRepository := repository.NewProductRepository(dbClient, config)

	productService := services.NewProductService(productRepository)
	pb.RegisterProductServiceServer(serverRegistration, productService)

	reflection.Register(serverRegistration)

	err = serverRegistration.Serve(listner)

	if err != nil {
		log.Fatal(err)
	}

}
