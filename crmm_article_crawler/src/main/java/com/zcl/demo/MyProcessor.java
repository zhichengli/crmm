package com.zcl.demo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class MyProcessor implements PageProcessor {
    @Override
    public void process(Page page) {
        //page.addTargetRequests(page.getHtml().links().all());
        System.out.println(page.getHtml().xpath("//*[@id=\"nav\"]/div/div/ul/li[5]/a").toString());
    }

    @Override
    public Site getSite() {
        return Site.me().setSleepTime(100).setRetryTimes(3);
    }

    public static void main(String[] args) {
        Spider.create(new MyProcessor()).addUrl("https://blog.csdn.net").run();
    }
}
