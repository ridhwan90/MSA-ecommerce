package services

import (
	"context"
	"log/slog"

	"github.com/ridhwan90/MSA-ecommerce/ProductService/models"
	pb "github.com/ridhwan90/MSA-ecommerce/ProductService/productServicePb/proto"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/repository"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"google.golang.org/grpc/codes"
	"google.golang.org/grpc/status"
)

type ProductService struct {
	pb.UnimplementedProductServiceServer
	productRepo repository.ProductRepository
}

func NewProductService(productRepo repository.ProductRepository) ProductService {
	return ProductService{productRepo: productRepo}
}

func (ps ProductService) GetProductById(ctx context.Context, request *pb.GetProductByIdRequest) (*pb.GetProductByIdResponse, error) {

	id, err := primitive.ObjectIDFromHex(request.Id)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, err.Error())
	}
	product, err := ps.productRepo.GetProductById(id, ctx)
	if err != nil {
		return nil, status.Error(codes.NotFound, err.Error())
	}

	skus := make([]*pb.SKU, len(product.SKU))

	for i, sku := range product.SKU {
		skus[i] = &pb.SKU{
			Sku:      sku.SKU,
			Quantity: sku.Quantity,
			ImageUrl: sku.ProductImage,
		}
	}

	return &pb.GetProductByIdResponse{Product: &pb.Product{
		Id:          product.ID,
		Name:        product.Name,
		Quantity:    product.Quantity,
		Description: product.Description,
		Price:       product.Price,
		ImageUrl:    product.ProductImage,
		Category:    &pb.Category{Name: product.Category.Name, Description: product.Category.Description},
		Skus:        skus,
		Discounts:   &pb.Discount{Name: product.Discount.Name, Discount: product.Discount.Percentage, Description: product.Discount.Description, StartDate: product.Discount.StartDate, EndDate: product.Discount.EndDate, IsActive: product.Discount.IsActive},
	}}, nil
}

func (ps ProductService) CreateProduct(ctx context.Context, request *pb.CreateProductRequest) (*pb.CreateProductResponse, error) {

	id := primitive.NewObjectID().String()

	var skus []models.SKU
	for _, sku := range request.Product.Skus {
		skus = append(skus, models.SKU{
			SKU:          sku.Sku,
			Quantity:     sku.Quantity,
			ProductImage: sku.ImageUrl,
		})
	}

	product := models.Product{
		ID:           id,
		Name:         request.Product.Name,
		Quantity:     request.Product.Quantity,
		Description:  request.Product.Description,
		Price:        request.Product.Price,
		ProductImage: request.Product.ImageUrl,
		Category:     models.ProductCategory{Name: request.Product.Category.Name, Description: request.Product.Category.Description},
		SKU:          skus,
		Discount:     models.Discount{Name: request.Product.Discounts.Name, Description: request.Product.Discounts.Description, Percentage: request.Product.Discounts.Discount, StartDate: request.Product.Discounts.StartDate, EndDate: request.Product.Discounts.EndDate, IsActive: request.Product.Discounts.IsActive},
	}

	oid, err := ps.productRepo.AddProduct(product, ctx)
	if err != nil {
		return nil, status.Error(codes.Internal, err.Error())
	}

	slog.Info("product id: " + id + " was created successfully")

	return &pb.CreateProductResponse{Id: oid}, nil
}

func (ps ProductService) DeleteProduct(ctx context.Context, request *pb.DeleteProductRequest) (*pb.DeleteProductResponse, error) {

	id, err := primitive.ObjectIDFromHex(request.Id)
	if err != nil {
		return nil, status.Error(codes.InvalidArgument, err.Error())
	}
	count, err := ps.productRepo.DeleteProduct(id, ctx)
	if err != nil {
		slog.Error(err.Error())
		return nil, status.Error(codes.Internal, err.Error())
	}
	slog.Info("product id: " + request.Id + " was deleted successfully")

	if count == 0 {
		// return new error saying that no product was deleted
		return nil, status.Error(codes.NotFound, "no product was deleted")
	}
	return &pb.DeleteProductResponse{IsSuccess: true, Message: "product deleted successfully"}, nil
}

func (ps ProductService) GetProducts(ctx context.Context, request *pb.GetProductsRequest) (*pb.GetProductsResponse, error) {

	products, err := ps.productRepo.ListProductsPagination(request.Limit, request.Offset, ctx)

	if err != nil {
		return nil, status.Error(codes.Internal, err.Error())
	}

	pbProducts := make([]*pb.Product, len(products))
	for i, product := range products {
		skus := make([]*pb.SKU, len(product.SKU))

		for j, sku := range product.SKU {
			skus[j] = &pb.SKU{
				Sku:      sku.SKU,
				Quantity: sku.Quantity,
				ImageUrl: sku.ProductImage,
			}
		}
		pbProducts[i] = &pb.Product{
			Id:          product.ID,
			Name:        product.Name,
			Quantity:    product.Quantity,
			Description: product.Description,
			Price:       product.Price,
			ImageUrl:    product.ProductImage,
			Category:    &pb.Category{Name: product.Category.Name, Description: product.Category.Description},
			Skus:        skus,
			Discounts:   &pb.Discount{Name: product.Discount.Name, Discount: product.Discount.Percentage, Description: product.Discount.Description, StartDate: product.Discount.StartDate, EndDate: product.Discount.EndDate, IsActive: product.Discount.IsActive},
		}
	}
	return &pb.GetProductsResponse{Products: pbProducts}, nil
}
