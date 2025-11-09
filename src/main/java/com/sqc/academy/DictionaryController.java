package com.sqc.academy;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Hãy xây dựng một API cho phép người dùng nhập một từ tiếng Anh và nhận về bản dịch tiếng Việt tương ứng.
 */
@RestController
public class DictionaryController {

    private static Map<String, String> dictionary = new HashMap<>();
    static {
        dictionary.put("hello", "xin chào");
        dictionary.put("apple", "quả táo");
        dictionary.put("banana", "quả chuối");
        dictionary.put("cat", "con mèo");
        dictionary.put("dog", "con chó");
        dictionary.put("food", "thức ăn");
        dictionary.put("sun", "mặt trời");
        dictionary.put("moon", "mặt trăng");
        dictionary.put("love", "tình yêu");
        dictionary.put("friend", "bạn bè");
        dictionary.put("home", "ngôi nhà");
        dictionary.put("music", "âm nhạc");
        dictionary.put("family", "gia đình");

    }

    @GetMapping("/dictionary")
    public ResponseEntity<String> translate(@RequestParam String word) {

        String cleanWord = word.trim().toLowerCase();

      // kiểm tra
        if (dictionary.containsKey(cleanWord)) {
            return ResponseEntity.ok(dictionary.get(cleanWord));
        } else {
            return ResponseEntity.status(404).body("Không tìm thấy từ này trong từ điển.");
        }
    }
}
