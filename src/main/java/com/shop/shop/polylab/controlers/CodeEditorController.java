package com.shop.shop.polylab.controlers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.*;

@Controller
public class CodeEditorController {

    @GetMapping("/execute")
    public String showEditor(Model model) {
        // Добавляем пустой код по умолчанию, если страница загружается впервые
        model.addAttribute("code", "");
        model.addAttribute("result", "");
        return "console";
    }

    @PostMapping("/execute")
    public String executeCode(@RequestParam("code") String code, Model model) {
        String result;
        try {
            // Создаем временный файл для скрипта
            File tempScript = createTempScript(code);

            // Запускаем Python скрипт через ProcessBuilder
            ProcessBuilder pb = new ProcessBuilder("python", tempScript.toString());
            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Считываем вывод скрипта
            StringWriter writer = new StringWriter();
            InputStream inputStream = process.getInputStream();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    writer.write(line + "\n");
                }
            }
            result = writer.toString();
        } catch (Exception e) {
            result = "Error: " + e.getMessage();
        }

        // Передаем результат и введённый код обратно в модель для отображения на странице
        model.addAttribute("code", code);
        model.addAttribute("result", result);
        return "console";
    }

    // Метод для создания временного Python-скрипта
    private File createTempScript(String code) throws IOException {
        File tempScript = File.createTempFile("script", ".py");
        try (Writer streamWriter = new OutputStreamWriter(new FileOutputStream(tempScript))) {
            streamWriter.write(code);
        }
        return tempScript;
    }
}