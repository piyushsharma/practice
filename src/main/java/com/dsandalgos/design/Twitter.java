package com.dsandalgos.design;

import java.util.*;

/**
 * Created by Piyush Sharma
 */

/*

Design a simplified version of Twitter where users can post tweets, follow/unfollow another user
 and is able to see the 10 most recent tweets in the user's news feed.

Your design should support the following methods:

1. postTweet(userId, tweetId): Compose a new tweet.
2. getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed.
   Each item in the news feed must be posted by users who the user followed or by the user herself.
   Tweets must be ordered from most recent to least recent.
3. follow(followerId, followeeId): Follower follows a followee.
4. unfollow(followerId, followeeId): Follower unfollows a followee.

*/

public class Twitter {

    class Tweet {
        int tweetId;
        int userId;
        public Tweet(int tweetId, int userId) {
            this.tweetId = tweetId;
            this.userId = userId;
        }
    }

    private Map<Integer, Set<Integer>> userToFeedUsersMap;
    private List<Tweet> tweets;

    /** Initialize your data structure here. */
    public Twitter() {
        userToFeedUsersMap = new HashMap<>();
        tweets = new ArrayList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        tweets.add(new Tweet(tweetId, userId));
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     *  Each item in the news feed must be posted by users who the user followed or by the user herself.
     *  Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> feed = new ArrayList<>();
        Set<Integer> allUsersInFeed = userToFeedUsersMap.get(userId);

        if(allUsersInFeed == null) allUsersInFeed = new HashSet<>();
        allUsersInFeed.add(userId); // always consider at least just myself when looking at feed

        for(int i = tweets.size() - 1; i >= 0; --i) {

            Tweet t = tweets.get(i);
            if(allUsersInFeed.contains(t.userId)) {
                feed.add(t.tweetId);
                if(feed.size() == 10) break;
            }
        }
        return feed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userToFeedUsersMap.containsKey(followerId)) {
            userToFeedUsersMap.put(followerId, new HashSet<>());
        }
        userToFeedUsersMap.get(followerId).add(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userToFeedUsersMap.containsKey(followerId)) {
            userToFeedUsersMap.put(followerId, new HashSet<>());
        }
        userToFeedUsersMap.get(followerId).remove(followeeId);
    }


    public static void main(String[] args) {
        Twitter twitter = new Twitter();

        twitter.postTweet(1, 5);

        List<Integer> l = twitter.getNewsFeed(1);
        for(Integer i : l) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        twitter.follow(1, 2);

        twitter.postTweet(2, 6);

        l = twitter.getNewsFeed(1);
        for(Integer i : l) {
            System.out.printf("%d ", i);
        }
        System.out.println();

        twitter.unfollow(1, 2);

        l = twitter.getNewsFeed(1);
        for(Integer i : l) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}