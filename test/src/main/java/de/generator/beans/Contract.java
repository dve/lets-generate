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

import org.rapidpm.vgu.generator.annotation.DataBean;
import org.rapidpm.vgu.generator.annotation.FilterProperty;
import org.rapidpm.vgu.generator.annotation.SortProperty;
import net.vergien.beanautoutils.annotation.Bean;

@DataBean
@Bean
public class Contract extends BaseEntity {
  @SortProperty(defaultSort = true)
  @FilterProperty(defaultFilter = true)
  private String name;
  @FilterProperty
  private Address address;

  public Contract() {
    super();
  }

  public Contract(int id, String name, Address address) {
    super(id);
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return ContractBeanUtil.doToString(this);
  }

  @Override
  public int hashCode() {
    return ContractBeanUtil.doToHashCode(this);
  }

  @Override
  public boolean equals(Object obj) {
    return ContractBeanUtil.doEquals(this, obj);
  }
}
