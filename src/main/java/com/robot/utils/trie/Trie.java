package com.robot.utils.trie;

import lombok.Data;

import java.util.HashMap;

/**
 * @author zhang.yu
 * @date 2022-09-13 10:06:56
 * @description
 */
@Data
public class Trie {
    private char chars;
    private HashMap<Character, Trie> map;
    private Boolean isEnd;

    public Trie() {
        this.map = new HashMap<>();
        this.isEnd = false;
    }

    public Trie(char c) {
        this.chars = c;
        this.map = new HashMap<>();
        this.isEnd = false;
    }

    /**
     * 插入新的敏感词
     *
     * @param word
     */
    public void insert(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!node.getMap().containsKey(c)) {
                node.map.put(c, new Trie(c));
            }
            node = node.getMap().get(c);
        }
        node.isEnd = true;
    }

    /**
     * 检索当前语句是否包含敏感词
     *
     * @param word
     * @return
     */
    public boolean search(String word) {
        Trie node = this;
        for (int i = 0; i < word.length(); i++) {
            Trie temp = node;
            for (int j = i; j < word.length(); j++) {
                char c = word.charAt(j);
                if (!temp.getMap().containsKey(c)) {
                    break;
                }
                temp = temp.getMap().get(c);
                if (temp != null && temp.isEnd) return true;
            }
        }
        return false;
    }
}
