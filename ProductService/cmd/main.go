package main

import (
	"log"
	"log/slog"
	"os"

	"github.com/joho/godotenv"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/db"
	grpcserver "github.com/ridhwan90/MSA-ecommerce/ProductService/grpcServer"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/utils"
)

func main() {
	logger := slog.New(slog.Default().Handler())

	logger.Info("Starting ProductService")

	config := read_config()

	dbClient, err := db.ConnectMongoDB(config.Database.Url)

	if err != nil {
		logger.Error(err.Error())
	}

	grpcserver.InitializeGrpcServer(&config, dbClient)

}

func read_config() utils.Configuration {

	err := godotenv.Load()
	if err != nil {
		log.Fatal("Error loading .env file")
	}

	mongoUri := os.Getenv("MONGO_URI")
	port := os.Getenv("PORT")
	dbName := os.Getenv("DB_NAME")
	collectionName := os.Getenv("COLLECTION_NAME")
	appName := os.Getenv("APP_NAME")

	if mongoUri == "" || port == "" || dbName == "" || collectionName == "" || appName == "" {
		log.Fatal("Missing required environment variables MONGO_URI, PORT, DB_NAME, COLLECTION_NAME, APP_NAME")
	}

	return utils.Configuration{
		App:      utils.Application{Name: appName},
		Database: utils.DatabaseSetting{Url: mongoUri, DbName: dbName, CollectionName: collectionName},
		Server:   utils.ServerSetting{Port: port},
	}
}
