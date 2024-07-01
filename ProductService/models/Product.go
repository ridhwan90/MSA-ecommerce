package models

type Product struct {
	ID           string          `json:"id" bson:"_id"`
	Name         string          `json:"name" bson:"name"`
	Price        float32         `json:"price" bson:"price"`
	Description  string          `json:"description" bson:"description"`
	Quantity     int32           `json:"quantity" bson:"quantity"`
	ProductImage string          `json:"product_image" bson:"product_image"`
	Category     ProductCategory `json:"category" bson:"category"`
	SKU          []SKU           `json:"sku" bson:"sku"`
	Discount     Discount        `json:"discount" bson:"discount"`
}

type ProductCategory struct {
	Name        string `json:"name" bson:"name"`
	Description string `json:"description" bson:"description"`
}

type SKU struct {
	SKU          string `json:"sku" bson:"sku"`
	Quantity     int32  `json:"quantity" bson:"quantity"`
	ProductImage string `json:"product_image" bson:"product_image"`
}

type Discount struct {
	Name        string  `json:"name" bson:"name"`
	Percentage  float32 `json:"percentage" bson:"percentage"`
	Description string  `json:"description" bson:"description"`
	StartDate   string  `json:"start_date" bson:"start_date"`
	EndDate     string  `json:"end_date" bson:"end_date"`
	IsActive    bool    `json:"is_active" bson:"is_active"`
}
