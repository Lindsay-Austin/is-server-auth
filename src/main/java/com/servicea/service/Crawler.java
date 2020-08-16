package com.servicea.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class Crawler {

    public void start() throws IOException {
        // write your code here
        Set<String> setUrls = new HashSet<>();
        for(int i = 1; i <= 5; i++)
        {
            String strUrl = "https://www.louisvuitton.cn/zhs-cn/products/favorite-mm-damier-ebene-008841";
            setUrls.add(strUrl);
        }

        Set<String> setProjUrls = new HashSet<>();
        for(String stringUrl : setUrls)
        {
            Document document = Jsoup.connect(stringUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
                    .post();

            Elements elements1 = document.select(".news-list");
            Elements elements = elements1.select("div.box");

            for(Element element : elements)
            {
                Elements eleUrl = element.select("div.box-aw a");
                String strPrjUrl = eleUrl.attr("href");
                setProjUrls.add(strPrjUrl);
                //  System.out.println(strPrjUrl);
                Elements eleTitle = eleUrl.select(".title");
                String strTitle = eleTitle.text();
                // System.out.println(strTitle);
                Elements eleSummary = eleUrl.select(".summary");
                String strSummary = eleSummary.text();
                //  System.out.println(strSummary);
            }
        }

        for(String stringUrl : setProjUrls)
        {
            Document document = Jsoup.connect(stringUrl)
                    .userAgent("Mozilla/5.0 (Windows NT 6.1; rv:30.0) Gecko/20100101 Firefox/30.0")
                    .get();
            Elements elements = document.select("div.box-aw a h1");

            String strTitle = elements.text();
            System.out.println("标题：" + strTitle);

            Elements elementsSection = document.select("section.list");

            int nSize = elementsSection.get(0).children().size();

            if(nSize == 0)
                continue;

            Element elementProtocol = elementsSection.get(0).child(0);
            Elements elesPro = elementProtocol.select("span");
            String strPro = elesPro.text();
            System.out.println("开源协议：" + strPro);

            nSize--;
            if(nSize == 0)
                continue;

            Element elementLan = elementsSection.get(0).child(1);
            Elements elesLan = elementLan.select("span").get(0).children();
            StringBuilder strlan = new StringBuilder();
            for(Element ele : elesLan)
            {
                String strLanTemp = ele.text();
                if(strLanTemp.indexOf("查看源码")>=0)
                    break;
                strlan.append(strLanTemp+",");
            }
            if(elesLan.size()>0)
            {
                String strLanguage = strlan.toString().substring(0,strlan.length()-1);
                System.out.println("开发语言：" + strLanguage);
            }

            nSize--;
            if(nSize == 0)
                continue;

            Element elementOS = elementsSection.get(0).child(2);
            Elements elesOS = elementOS.select("span");
            String strOS = elesOS.text();
            System.out.println("操作系统：" + strOS);

            nSize--;
            if(nSize == 0)
                continue;

            Element elementAuthor = elementsSection.get(0).child(3);
            Elements elesAuthor = elementAuthor.select("a.link");
            String strAuthor= elesAuthor.text();
            System.out.println("软件作者；" + strAuthor);

            System.out.println("---------------------");
        }

        System.out.println("over!!!!!");

    }
}
