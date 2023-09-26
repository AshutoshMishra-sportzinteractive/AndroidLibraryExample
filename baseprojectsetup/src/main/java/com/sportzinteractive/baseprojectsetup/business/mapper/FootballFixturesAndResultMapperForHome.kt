package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballFixturesAndResults
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballMatchListDateWise
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.FootballFixturesAndResult
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.Matches
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils.convertDateStringToSpecifiedDateString
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils.getCurrentDate
import javax.inject.Inject

class FootballFixturesAndResultMapperForHome @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<FootballFixturesAndResult, FootballFixturesAndResults> {
    override fun toDomain(entity: FootballFixturesAndResult): FootballFixturesAndResults {
        val map = mutableMapOf<String?, List<Matches?>?>()
        val filteredMap = mutableListOf<FootballMatchListDateWise>()

        val listWithModifiedEventState = entity.matches?.sortedBy { it?.startDate }?.map {
            if (CalendarUtils.isDatePast(CalendarUtils.DOB_DATE_FORMAT,it?.startDate?:"",getCurrentDate()))
            {
                if (it?.eventState == "U"){
                    it.copy(eventStateTemp = "R")
                }else{
                    it?.copy(eventStateTemp = it?.eventState)
                }
            }else{
                it?.copy(eventStateTemp = it.eventState)
            }
        }
        val liveMatchList = listWithModifiedEventState?.filter { it?.eventStateTemp == "L" }
        val upcomingMatchList = listWithModifiedEventState?.filter { it?.eventStateTemp == "U" }
        val resultMatchList = listWithModifiedEventState?.filter { it?.eventStateTemp == "R" }

        var previousCount = entity.prevCount
        val nextCount = entity.nextCount

        val listOfDates = entity.matches?.sortedBy { it?.startDate }?.map {
            convertDateStringToSpecifiedDateString(
                dateString = it?.startDate,
                dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
            )
        }?.distinct()

        listOfDates?.forEach { date ->
            val listOfMatches = entity.matches.filter { match ->
                convertDateStringToSpecifiedDateString(
                    dateString = match?.startDate,
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                ) == date
            }
            map[date] = listOfMatches
        }

        if (liveMatchList?.isNotEmpty() == true) {
            val pastDate = CalendarUtils.getPreviousOrFutureDate(CalendarUtils.DOB_DATE_FORMAT, -1)
            val item = map[pastDate]
            val list = item?.filter { it?.eventState == "L" }
            list?.let {
                if(it.isNotEmpty()){
                    filteredMap.add(
                        FootballMatchListDateWise(
                            true,
                            convertDateStringToSpecifiedDateString(
                                dateString = pastDate,
                                dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                                requiredDateFormat = CalendarUtils.MATCH_DAY
                            ) ?: "",
                            pastDate,
                            toMatchObjet(it)
                        )
                    )
                    previousCount--
                }
            }
        }
        if (resultMatchList?.size!! == entity.matches.size) {
            var j = 0
            while (j < previousCount) {
                val date = listOfDates?.reversed()?.get(j)
                val item = map[date]
                item?.let {
                    filteredMap.add(
                        FootballMatchListDateWise(
                            false,
                            convertDateStringToSpecifiedDateString(
                                dateString = date,
                                dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                                requiredDateFormat = CalendarUtils.MATCH_DAY
                            )?:"",
                            date?:"",
                            toMatchObjet(it)
                        )
                    )
                }
                j++
            }
        } else if (resultMatchList.isNotEmpty() && resultMatchList.size < entity.matches.size) {
            val lastRecentMatch = resultMatchList.last()
            map.onEachIndexed { index, entry ->
                if (entry.key == convertDateStringToSpecifiedDateString(
                        dateString = lastRecentMatch?.startDate,
                        dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                        requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                    )
                ) {
                    var i = previousCount
                    var tp = if (convertDateStringToSpecifiedDateString(
                            dateString = lastRecentMatch?.startDate,
                            dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                            requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                        ) == getCurrentDate()
                    ) index - 1 else index
                    while (tp >= 0 && i > 0) {
                        val date = listOfDates?.get(tp)
                        val item = map[date]
                        item?.let { it ->
                            filteredMap.add(
                                FootballMatchListDateWise(
                                    false,
                                    convertDateStringToSpecifiedDateString(
                                        dateString = date,
                                        dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                                        requiredDateFormat = CalendarUtils.MATCH_DAY
                                    )?:"",
                                    date?:"",
                                    toMatchObjet(it)
                                )
                            )
                        }
                        i--
                        tp--
                    }
                }
            }
        }
        if (map.containsKey(getCurrentDate())) {
            var date = getCurrentDate()
            val item = map[date]
            item?.let {
                filteredMap.add(
                    FootballMatchListDateWise(
                        item.any { match-> match?.eventState=="L" },
                        convertDateStringToSpecifiedDateString(
                            dateString = date,
                            dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                            requiredDateFormat = CalendarUtils.MATCH_DAY
                        )?:"",
                        date?:"",
                        toMatchObjet(it)
                    )
                )
            }
        }
        if (upcomingMatchList?.size == entity.matches.size) {
            var j = 0
            while (j < nextCount) {
                val date = listOfDates?.get(j)
                val item = map[date]
                item?.let {
                    filteredMap.add(
                        FootballMatchListDateWise(
                            false,
                            convertDateStringToSpecifiedDateString(
                                dateString = date,
                                dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                                requiredDateFormat = CalendarUtils.MATCH_DAY
                            )?:"",
                            date?:"",
                            toMatchObjet(it)
                        )
                    )
                }
                j++
            }
        } else if (upcomingMatchList?.size!! > 0 && upcomingMatchList.size < entity.matches.size) {
            val firstUpcomingMatch = upcomingMatchList.first()
            map.onEachIndexed { index, entry ->
                if (entry.key == convertDateStringToSpecifiedDateString(
                        dateString = firstUpcomingMatch?.startDate,
                        dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                        requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                    )
                ) {
                    var i = nextCount
                    var tp =
                        if (convertDateStringToSpecifiedDateString(
                                dateString = firstUpcomingMatch?.startDate,
                                dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                                requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                            ) == getCurrentDate()
                        ) index + 1 else index
                    while (tp < map.size && i > 0) {
                        val date = listOfDates?.get(tp)
                        val item = map[date]
                        item?.let { it ->
                            filteredMap.add(
                                FootballMatchListDateWise(
                                    false,
                                    convertDateStringToSpecifiedDateString(
                                        dateString = date,
                                        dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                                        requiredDateFormat = CalendarUtils.MATCH_DAY
                                    )?:"",
                                    date?:"",
                                    toMatchObjet(it)
                                )
                            )
                        }
                        i--
                        tp++
                    }
                }
            }
        }

        return FootballFixturesAndResults(
            filteredMap.firstOrNull { it.isLiveMatch },
            if (entity.sortOrder==0) filteredMap.sortedBy { it.matchDay } else {filteredMap.sortedByDescending { it.matchDay }},
            upcomingMatchList?.let { toMatchObjet(upcomingMatchList) } ?: listOf(),null
        )
    }

    private fun toMatchObjet(matches: List<Matches?>): List<com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.Matches> {
        return matches.map { match ->
            com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.Matches(
                endDate = match?.endDate,
                eventFormat = match?.eventFormat,
                eventGroup = match?.eventGroup,
                eventIsLinkable = match?.eventIslinkable,
                eventName = match?.eventName,
                eventStage = match?.eventStage,
                eventState = match?.eventState,
                eventStateTemp = "",
                eventStatus = match?.eventStatus,
                eventStatusId = match?.eventStatusId,
                eventSubStatus = match?.eventSubStatus,
                gameId = match?.gameId,
                isRescheduled = match?.isRescheduled,
                isTbc = match?.isTbc,
                leagueCode = match?.leagueCode,
                participants = match?.participants?.map { participants ->
                    com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.Participant(
                        highlight = participants?.highlight,
                        id = participants?.id,
                        name = participants?.name,
                        playersInvolved = participants?.playersInvolved,
                        value = participants?.value,
                        teamLogo = baseInfo.getTeamLogo(participants?.id ?: ""),
                        shortName = participants?.shortName
                    )
                },
                resultCode = match?.resultCode,
                resultSubCode = match?.resultSubCode,
                seriesId = match?.seriesId,
                seriesName = match?.seriesName,
                sport = match?.sport,
                formatedStartDate = convertDateStringToSpecifiedDateString(
                    dateString = match?.startDate,
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.MATCH_TIME
                ),
                startDate = convertDateStringToSpecifiedDateString(
                    match?.startDate ?: "",
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.MATCH_REQUIRED_DATE_FORMAT
                ),
                startDateWithTime=match?.startDate,
                tourId = match?.tourId,
                tourName = match?.tourName,
                venueId = match?.venueId,
                venueName = match?.venueName,
                winningMargin = match?.winningMargin
            )
        }
    }
}