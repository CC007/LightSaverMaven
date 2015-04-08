/**
 * ********************************************************************
 * Copyright (c) 2003 Andy Jefferson and others. All rights reserved. Licensed
 * under the Apache License, Version 2.0 (the "License"); you may not use this
 * file except in compliance with the License. You may obtain a copy of the
 * License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Contributors: ...
*********************************************************************
 */
package com.github.cc007.lightsaver.datacontroller.storage;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * Definition of a Entry Represents a product, and contains the key aspects of
 * the item.
 */
@PersistenceCapable
public class Entry {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.NATIVE)
    protected long id;

    protected int clientId = 0;

    protected int price = 0;

    protected long date = 0;

    public Entry(int clientId, int price, long date) {
        this.clientId = clientId;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public int getClientId() {
        return clientId;
    }

    public int getPrice() {
        return price;
    }

    public void setName(int clientId) {
        this.clientId = clientId;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
    

    @Override
    public String toString() {
        return "Entry : " + id + " name=" + clientId + " [" + date + "]";
    }
}
