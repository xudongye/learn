package me.own.learn.pubaccount.service;

import java.util.Map;

public interface SignService {
    Map<String, Object> signUrl(String appId, String url);
}
