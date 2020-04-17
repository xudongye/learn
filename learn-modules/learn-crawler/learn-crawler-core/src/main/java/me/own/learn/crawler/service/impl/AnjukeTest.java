package me.own.learn.crawler.service.impl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

/**
 * @author: yexudong
 * @Date: 2020/4/14 14:19
 */
public class AnjukeTest {


    public static void main(String[] args) {
        Document document = null;
        String url = "https://sh.zu.anjuke.com/fangyuan/1430109344111628?isauction=2&shangquan_id=7084&legoFeeUrl=https%3A%2F%2Flegoclick.58.com%2Fjump%3Ftarget%3DpZwY0ZnlsztdraOWUvYKuaY3uywhnHNvuBYkPvnYsHw6nhmVrAu6nzY3PvEYujKhuhwbuycKnHTkrHnknWb1TEDQPjnknHTOn1EYnHDQPWc3THD1PHTkTHD1PHTkTHD_nHTKnBkvrH0Osj0krjEKnHN3PW9dnjm1nHTQn9DQTNujsRKjsN72izLMGO4hBstVOlvUlmaFOmBgl2AClpAdTHDKnEDKsHDKTHDzPjbOPjNkPHmknjmQnHbYnWEKP971pAblnkDQTyEknjEkP1b1sHK6mWEVPj6-mid6mhRhsHT3n1NYPH0vmW6WrTDQnWEOrHEdnjNvnHEYPHE1P1EYTHDzPjbOPjNkPHmkPWbkn1nOrjEKTEDKTEDVTEDKpZwY0Znlszq1paOlIiO6UhGdpvN8mvqVsvu6UhIOIy78sLPfUhIJpy78uzdQsgPGph-8uzqhXjnfTNwjnHIKrj9ksHRKrDmVPjT3riYvE1wjsH9Lwbmzn1EkE19LnkDQnHm8nWc3sW9dsWcYn9DkTHTKTHD_nHDKXLYKnHTkn1mKnHTknHmvPE7huj01nj91PaYzPAcdsHwhmvcVmWNQmzd-uW9knjPhnjN3uAmKTHEKTED1THc_PWbLrikLnj9YTHDKmHnkuHEknhF-m1RhuANdrE";
        try {
            document =
            Jsoup.connect(url).get();
            Elements scriptElement = document.getElementsByClass("img_wrap");
            System.out.println(document);
        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
