package letsgenerate;

import org.rapidpm.vgu.generator.annotation.DataBean;
import org.rapidpm.vgu.generator.annotation.FilterProperty;
import org.rapidpm.vgu.generator.annotation.SortProperty;

@DataBean
public class SimpleBean {
  @SortProperty(defaultSort = true)
  @FilterProperty
  private int id;
  @SortProperty
  @FilterProperty(defaultFilter = true)
  private String name;
}
