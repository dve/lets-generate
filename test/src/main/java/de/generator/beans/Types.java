/**
 * Copyright © 2019 Daniel Nordhoff-Vergien (dve@vergien.net)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.generator.beans;

import javax.validation.constraints.NotNull;
import org.rapidpm.vgu.generator.annotation.DataBean;
import org.rapidpm.vgu.generator.annotation.FilterProperty;
import org.rapidpm.vgu.generator.annotation.SortProperty;
import net.vergien.beanautoutils.annotation.Bean;

@Bean
@DataBean
public class Types extends BaseEntity {
  @SortProperty(defaultSort = true)
  private int intType;
  private Integer integerType;
  private double doubleType;
  private Double doubleClassType;
  private long longType;
  private Long longClassType;
  private float floatType;
  private Float floatClassType;
  @FilterProperty(defaultFilter = true)
  private String stringType;
  @NotNull
  private String requieredString;

  public int getIntType() {
    return intType;
  }

  public void setIntType(int intType) {
    this.intType = intType;
  }

  public Integer getIntegerType() {
    return integerType;
  }

  public void setIntegerType(Integer integerType) {
    this.integerType = integerType;
  }

  public double getDoubleType() {
    return doubleType;
  }

  public void setDoubleType(double doubleType) {
    this.doubleType = doubleType;
  }

  public Double getDoubleClassType() {
    return doubleClassType;
  }

  public void setDoubleClassType(Double doubleClassType) {
    this.doubleClassType = doubleClassType;
  }

  public long getLongType() {
    return longType;
  }

  public void setLongType(long longType) {
    this.longType = longType;
  }

  public Long getLongClassType() {
    return longClassType;
  }

  public void setLongClassType(Long longClassType) {
    this.longClassType = longClassType;
  }

  public float getFloatType() {
    return floatType;
  }

  public void setFloatType(float floatType) {
    this.floatType = floatType;
  }

  public Float getFloatClassType() {
    return floatClassType;
  }

  public void setFloatClassType(Float floatClassType) {
    this.floatClassType = floatClassType;
  }

  public String getStringType() {
    return stringType;
  }

  public void setStringType(String stringType) {
    this.stringType = stringType;
  }

  public String getRequieredString() {
    return requieredString;
  }

  public void setRequieredString(String requieredString) {
    this.requieredString = requieredString;
  }

  @Override
  public String toString() {
    return TypesBeanUtil.doToString(this);
  }

  @Override
  public int hashCode() {
    return TypesBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return TypesBeanUtil.doEquals(this, obj);
  }
}
