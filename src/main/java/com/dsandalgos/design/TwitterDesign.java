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

public class TwitterDesign {

    private Map<Integer, User> userIdToUserModel;
    private static int logicalTimestamp;

    class Tweet {
        int tweetId;
        int userId;
        int logicalTs;
        Tweet next;
        public Tweet(int tweetId, int userId, int logicalTs) {
            this.tweetId = tweetId;
            this.userId = userId;
            this.logicalTs = logicalTs;
            this.next = null;
        }
    }

    class User {

        Integer userId;
        Tweet tweetHead;
        Set<User> following;

        public User(int userId) {
            this.userId = userId;
            this.following = new HashSet<>();
        }

        public void follow(User user) {
            following.add(user);
        }

        public void unfollow(User user) {
            following.remove(user);
        }

        public void addUserTweet(Tweet t) {
            // insert at head
            t.next = tweetHead;
            tweetHead = t;
        }
    }

    /** Initialize your data structure here. */
    public TwitterDesign() {
        userIdToUserModel = new HashMap<>();
        this.logicalTimestamp = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet t = new Tweet(tweetId, userId, ++logicalTimestamp);

        if(!userIdToUserModel.containsKey(userId)) {
            userIdToUserModel.put(userId, new User(userId));
        }
        userIdToUserModel.get(userId).addUserTweet(t);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed.
     *  Each item in the news feed must be posted by users who the user followed or by the user herself.
     *  Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {

        List<Integer> feed = new ArrayList<>();
        User curUser = userIdToUserModel.get(userId);
        if(curUser == null) return feed;

        Set<User> allUsersInFeed = curUser.following;

        if(allUsersInFeed == null) allUsersInFeed = new HashSet<>();
        allUsersInFeed.add(curUser); // always consider at least just myself when looking at feed

        // now it is like merging k sorted lists as per logicalTimestamp for the top 10 most recent tweets
        // note we will need the queue to be maxHeap as we are interested in the last 10 tweets
        Queue<Tweet> pq = new PriorityQueue<>(10, new Comparator<Tweet>() {
            @Override
            public int compare(Tweet o1, Tweet o2) {
                return o2.logicalTs - o1.logicalTs;
            }
        });

        // add the tweet head from all users to the priority queue
        for(User u : allUsersInFeed) {
            if(u.tweetHead != null) {
                pq.add(u.tweetHead);
            }
        }

        while(!pq.isEmpty()) {
            Tweet temp = pq.poll();

            if(temp.next != null) {
                pq.add(temp.next);
            }

            feed.add(temp.tweetId);
            if(feed.size() >= 10) break;
        }

        return feed;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(!userIdToUserModel.containsKey(followerId)) {
            userIdToUserModel.put(followerId, new User(followerId));
        }
        if(!userIdToUserModel.containsKey(followeeId)) {
            userIdToUserModel.put(followeeId, new User(followeeId));
        }
        userIdToUserModel.get(followerId).follow(userIdToUserModel.get(followeeId));
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(!userIdToUserModel.containsKey(followerId)) {
            userIdToUserModel.put(followerId, new User(followerId));
        }
        if(!userIdToUserModel.containsKey(followeeId)) {
            userIdToUserModel.put(followeeId, new User(followeeId));
        }
        userIdToUserModel.get(followerId).unfollow(userIdToUserModel.get(followeeId));
    }


    public static void main(String[] args) {
        TwitterDesign twitter = new TwitterDesign();

        twitter.postTweet(1, 5);

        twitter.unfollow(1, 1);

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