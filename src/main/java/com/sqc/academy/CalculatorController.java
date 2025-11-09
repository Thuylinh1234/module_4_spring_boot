package com.sqc.academy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * API thực hiện các phép tính cơ bản: +, -, *, /
 */
@RestController
public class CalculatorController {

    @GetMapping("/calculator")
    public ResponseEntity<String> calculator(
            @RequestParam(value = "firstNumber", defaultValue = "") String firstNumber,
            @RequestParam(value = "secondNumber", defaultValue = "") String secondNumber,
            @RequestParam(value = "operator", defaultValue = "") String operator) {

        try {
            if (firstNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("Số thứ nhất không được để trống.");
            } else if (secondNumber.isEmpty()) {
                return ResponseEntity.badRequest().body("Số thứ hai không được để trống.");
            } else if (operator.isEmpty()) {
                return ResponseEntity.badRequest().body("Phép toán không được để trống.");
            }



            double num1 = Double.parseDouble(firstNumber);
            double num2 = Double.parseDouble(secondNumber);

            double result;

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 == 0) {
                        return ResponseEntity.badRequest().body("Không được chia cho 0.");
                    }
                    result = num1 / num2;
                    break;
                default:
                    return ResponseEntity.badRequest().body("Phép toán không hợp lệ. Chỉ được dùng: +, -, *, /");
            }


            return ResponseEntity.ok("Kết quả: " + result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Đã xảy ra lỗi: " + e.getMessage());
        }
    }


    private boolean isDouble(String chuoi) {
        try {
            Double.parseDouble(chuoi);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
