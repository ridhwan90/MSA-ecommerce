package repository

import (
	"context"

	"github.com/ridhwan90/MSA-ecommerce/ProductService/models"
	"github.com/ridhwan90/MSA-ecommerce/ProductService/utils"
	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/bson/primitive"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"
)

type ProductRepository interface {
	AddProduct(product models.Product, ctx context.Context) (string, error)
	GetProductById(id primitive.ObjectID, ctx context.Context) (*models.Product, error)
	ListProductsPagination(limit int32, offset int32, ctx context.Context) ([]*models.Product, error)
	DeleteProduct(id primitive.ObjectID, ctx context.Context) (int64, error)
}

type productRepository struct {
	client *mongo.Client
	config *utils.Configuration
}

func NewProductRepository(client *mongo.Client, config *utils.Configuration) ProductRepository {
	return &productRepository{client: client, config: config}
}

func (p *productRepository) AddProduct(product models.Product, ctx context.Context) (string, error) {

	collection := p.client.Database(p.config.Database.DbName).Collection(p.config.Database.CollectionName)

	insertResult, err := collection.InsertOne(ctx, product)

	if err != nil {
		return "", err
	}
	return insertResult.InsertedID.(string), nil

}

func (p *productRepository) GetProductById(id primitive.ObjectID, ctx context.Context) (*models.Product, error) {

	collection := p.client.Database(p.config.Database.DbName).Collection(p.config.Database.CollectionName)

	filter := bson.D{primitive.E{Key: "_id", Value: id.String()}}
	var product *models.Product

	err := collection.FindOne(ctx, filter).Decode(&product)
	if err != nil {
		return nil, err
	}
	return product, nil
}

func (p *productRepository) ListProductsPagination(limit int32, offset int32, ctx context.Context) ([]*models.Product, error) {

	skiping := offset * limit
	findOptions := options.Find()
	findOptions.SetLimit(int64(limit))
	findOptions.SetSkip(int64(skiping))
	collection := p.client.Database(p.config.Database.DbName).Collection(p.config.Database.CollectionName)

	var products []*models.Product
	cursor, err := collection.Find(ctx, bson.D{}, findOptions)
	if err != nil {
		return nil, err
	}
	cursor.All(ctx, &products)
	return products, nil
}

func (p *productRepository) DeleteProduct(id primitive.ObjectID, ctx context.Context) (int64, error) {

	collection := p.client.Database(p.config.Database.DbName).Collection(p.config.Database.CollectionName)

	filter := bson.D{primitive.E{Key: "_id", Value: id.String()}}
	result, err := collection.DeleteOne(ctx, filter)
	if err != nil {
		return 0, bson.ErrDecodeToNil
	}
	return result.DeletedCount, nil
}
