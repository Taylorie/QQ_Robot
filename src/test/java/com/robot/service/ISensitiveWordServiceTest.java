package com.robot.service;

import com.robot.utils.trie.GlobalTrie;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhang.yu
 * @date 2022-09-13 11:01:05
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ISensitiveWordServiceTest {
    @Autowired
    ISensitiveWordService sensitiveWordService;

    @Test
    public void add() throws Exception {
//        File file = new File("E:\\sensi_words.txt");
//        InputStreamReader inputStreamReader = new InputStreamReader(
//                new FileInputStream(file), "UTF-8"
//        );
//        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//        String line;
//        ArrayList<String> list = new ArrayList<>();
//        while ((line = bufferedReader.readLine()) != null) {
//            list.add(line);
//        }
//        bufferedReader.close();
//        inputStreamReader.close();
//        sensitiveWordService.addSensi(list);
        sensitiveWordService.generTrie();
        System.out.println(GlobalTrie.trie.search("8成人民日报人BT7863736"));
    }


}