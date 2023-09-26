package com.sportzinteractive.baseprojectsetup.business.domain.videos

enum class VideosViewType(val id:Int) {
    HIGHLIGHTS(1),
    TOP_MOMENTS(2),
    TOP_GOALS(3),
    INTERVIEWS(4),
    GAMING_HUB(id = 10),
    GOAL_OF_WEEK(16),
    UNKNOWN(-1)
}