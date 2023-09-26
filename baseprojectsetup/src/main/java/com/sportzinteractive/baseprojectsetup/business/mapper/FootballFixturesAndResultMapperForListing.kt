package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballFixturesAndResults
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballfixtures.FootballMatchListDateWise
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.FootballFixturesAndResult
import com.sportzinteractive.baseprojectsetup.data.model.footballfixtures.Matches
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject

class FootballFixturesAndResultMapperForListing @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<FootballFixturesAndResult, FootballFixturesAndResults> {
    override fun toDomain(entity: FootballFixturesAndResult): FootballFixturesAndResults {
        val listOfAllMatches = entity.clubId?.let { clubId ->
            entity.matches.filter { match ->
                match?.participants?.any { it?.id == clubId } == true
            }
        }?:run {
            entity.matches
        }

        val filteredMap = mutableListOf<FootballMatchListDateWise>()
        var liveMatchPosition:Int?=null
        val upcomingMatchList = listOfAllMatches?.filter { it?.eventState == "U" && !CalendarUtils.isDatePast(CalendarUtils.DOB_DATE_FORMAT,it?.startDate?:"", CalendarUtils.getCurrentDate()) }


        val listOfDates = listOfAllMatches?.sortedBy { it?.startDate }?.map {
            CalendarUtils.convertDateStringToSpecifiedDateString(
                dateString = it?.startDate,
                dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
            )
        }?.distinct()

        listOfDates?.forEachIndexed { index, date ->
            val listOfMatches = listOfAllMatches.filter { match ->
                CalendarUtils.convertDateStringToSpecifiedDateString(
                    dateString = match?.startDate,
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.DOB_DATE_FORMAT
                ) == date
            }
            listOfMatches.firstOrNull { it?.eventState == "L" }?.let {
                liveMatchPosition = index
            }
            filteredMap.add(
                FootballMatchListDateWise(
                    listOfMatches.any { match -> match?.eventState == "L" },
                    CalendarUtils.convertDateStringToSpecifiedDateString(
                        dateString = date,
                        dateFormat = CalendarUtils.DOB_DATE_FORMAT,
                        requiredDateFormat = CalendarUtils.MATCH_DAY
                    ) ?: "",
                    date?:"",
                    toMatchObjet(listOfMatches)
                )
            )
        }

        return FootballFixturesAndResults(
            filteredMap.firstOrNull { it.isLiveMatch },
            filteredMap,
            upcomingMatchList?.let { toMatchObjet(upcomingMatchList) } ?: listOf(),
            liveMatchPosition
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
                formatedStartDate = CalendarUtils.convertDateStringToSpecifiedDateString(
                    dateString = match?.startDate,
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.MATCH_TIME
                ),
                startDate = CalendarUtils.convertDateStringToSpecifiedDateString(
                    match?.startDate ?: "",
                    dateFormat = CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.MATCH_REQUIRED_DATE_FORMAT
                ),
                startDateWithTime=match?.startDate,
                tourId = match?.tourId,
                tourName = match?.tourName,
                venueId = match?.venueId,
                venueName = match?.venueName,
                eventStateTemp = "",
                winningMargin = match?.winningMargin
            )
        }
    }
}