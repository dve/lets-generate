/*
 * Copyright © 2021 Daniel Nordhoff-Vergien (dve@vergien.net)
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

import org.rapidpm.vgu.generator.annotation.DisplayReadOnly;
import org.rapidpm.vgu.generator.annotation.SortProperty;

public abstract class BaseEntity {
  @SortProperty
  @DisplayReadOnly
  private int id;

  public BaseEntity(int id) {
    super();
    this.id = id;
  }

  public BaseEntity() {
    super();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}
