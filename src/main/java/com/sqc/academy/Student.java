package com.sqc.academy;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder // thiết lập giá trị cho từng thuộc tính 1 cách dễ dàng
@NoArgsConstructor // ko tham số
@AllArgsConstructor // có tham có
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student {
     int id;
     String  name;
     double score;

}
