package generator.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * hsst
 * 2021-04-21T09:37:27.794
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 类别名称
     */
    private String name;

    /**
     * 标准分
     */
    private Integer criterionScore;

    /**
     * 水库类型
     */
    private String reservoirType;

    /**
     * 备注
     */
    private String remark;

    private static final long serialVersionUID = 1L;
}