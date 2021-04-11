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
package org.rapidpm.vgu.vaadin;

import java.util.ArrayList;
import java.util.List;
import com.vaadin.flow.data.provider.AbstractBackEndDataProvider;
import com.vaadin.flow.data.provider.Query;

public abstract class SizeChangeAwareDataProvider<T, F> extends AbstractBackEndDataProvider<T, F> {

  int lastSize = 0;

  private final List<SizeChangeEventListener> sizeChangeEventListeners = new ArrayList<>();

  @Override
  public int size(Query<T, F> query) {
    int size = super.size(query);

    if (lastSize != size) {
      onSizeChange(size);
    }
    lastSize = size;
    return size;
  }

  private void onSizeChange(int size) {
    for (SizeChangeEventListener sizeChangeEventListener : sizeChangeEventListeners) {
      sizeChangeEventListener.onSizeChange(size);
    }
  }

  public void addSizeChangeListener(SizeChangeEventListener listener) {
    sizeChangeEventListeners.add(listener);
  }
}
