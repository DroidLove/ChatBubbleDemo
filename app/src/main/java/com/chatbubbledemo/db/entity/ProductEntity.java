/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.chatbubbledemo.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.chatbubbledemo.db.model.Product;


@Entity(tableName = "products")
public class ProductEntity implements Product {
    @PrimaryKey
    private int id;
    private String productType;
    private String name;
    private String description;
    private int price;
    private int priceOffer;
    private double offDiscount;
    private String imageUrl;
    private int itemQuantity;
    private String weight;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getPriceOffer() {
        return priceOffer;
    }

    public void setPriceOffer(int priceOffer) {
        this.priceOffer = priceOffer;
    }

    @Override
    public double getOffDiscount() {
        return offDiscount;
    }

    public void setOffDiscount(double offDiscount) {
        this.offDiscount = offDiscount;
    }

    @Override
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public ProductEntity() {
    }

//    public ProductEntity(Product product) {
//        this.id = product.getId();
//        this.name = product.getName();
//        this.description = product.getDescription();
//        this.priceRegular = product.getPrice();
//    }


//    public ProductEntity(int id, String name, String description, int price, int priceOffer, double offDiscount, String imageUrl, int itemQuantity) {
//        this.id = id;
//        this.name = name;
//        this.description = description;
//        this.price = price;
//        this.priceOffer = priceOffer;
//        this.offDiscount = offDiscount;
//        this.imageUrl = imageUrl;
//        this.itemQuantity = itemQuantity;
//    }
}
