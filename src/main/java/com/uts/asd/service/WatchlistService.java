package com.uts.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uts.asd.mapper.WatchlistMapper;

/*
 * @author Harold Seefeld
 */

@Service
public class WatchlistService {
    @Autowired
    private static WatchlistMapper watchlistMapper;

    public static void addPropertyToWatchlist(String id) {
        watchlistMapper.addPropertyToWatchlist(id);
    }
}
