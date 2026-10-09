package com.example.util

import android.content.Context
import android.content.Intent

object ShareHelper {

    fun shareSolvedBrand(context: Context, brandName: String, category: String, score: Int) {
        val text = "🎉 I just identified the $category brand '$brandName' in Guess The Brand! Total Score: $score pts. Can you beat my high score? 🏆 #GuessTheBrand #LogoQuiz"
        launchShareIntent(context, "Share Achievement", text)
    }

    fun shareDailyStreak(context: Context, streak: Int) {
        val text = "🔥 I am on a $streak-Day Streak in Guess The Brand Daily Challenge! Can you match my trivia streak? Download & play now! 🎯 #DailyChallenge #GuessTheBrand"
        launchShareIntent(context, "Share Daily Streak", text)
    }

    fun shareAskForHelp(context: Context, category: String, scrambledLetters: String, length: Int) {
        val text = "🤔 Help me out! I am stuck on a $category brand with $length letters in Guess The Brand. Available letters: $scrambledLetters. What is the brand? 🔍 #GuessTheBrand"
        launchShareIntent(context, "Ask a Friend for Help", text)
    }

    fun shareLeaderboardInvite(context: Context, username: String, rank: Int, score: Int) {
        val text = "👑 I'm ranked #$rank with $score points on the Guess The Brand Global Leaderboard! Join my league and compete with me: [$username]! 🎮 #LogoQuiz #Leaderboard"
        launchShareIntent(context, "Challenge Friends", text)
    }

    private fun launchShareIntent(context: Context, title: String, text: String) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, title).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(shareIntent)
    }
}
