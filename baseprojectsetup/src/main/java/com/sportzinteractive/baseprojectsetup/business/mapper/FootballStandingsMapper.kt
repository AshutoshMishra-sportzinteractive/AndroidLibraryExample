package com.sportzinteractive.baseprojectsetup.business.mapper

import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.FootballStandingsData
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.Match
import com.sportzinteractive.baseprojectsetup.business.domain.model.footballstandings.Team
import com.sportzinteractive.baseprojectsetup.constants.BaseInfo
import com.sportzinteractive.baseprojectsetup.data.model.footballstandings.FootballStandings
import com.sportzinteractive.baseprojectsetup.helper.EntityMapper
import com.sportzinteractive.baseprojectsetup.utils.CalendarUtils
import javax.inject.Inject


class FootballStandingsMapper @Inject constructor(
    private val baseInfo: BaseInfo
) : EntityMapper<FootballStandings, FootballStandingsData> {
    override fun toDomain(entity: FootballStandings): FootballStandingsData {
        val group = entity.standings?.groups?.firstOrNull()
        val list = group?.let { group ->
            group.teams?.team?.map {
                Team(
                    teamId = it?.teamId,
                    teamGlobalId = it?.teamGlobalId,
                    teamName = it?.teamName,
                    match = getListOfMatches(it?.matchResult?.match?.sortedBy { it?.id }),
                    teamShortName = it?.teamShortName,
                    teamDisplayName = it?.teamDisplayName,
                    position = it?.position,
                    prevPosition = it?.prevPosition,
                    positionStatus = it?.positionStatus,
                    played = it?.played,
                    wins = it?.wins,
                    lost = it?.lost,
                    tied = it?.tied,
                    draws = it?.draws,
                    noResult = it?.noresult,
                    scoreDiff = it?.scoreDiff,
                    pointsConceded = it?.pointsConceded,
                    pointsScored = it?.pointsScored,
                    points = it?.points,
                    carryForwardPoints = it?.carryForwardPoints,
                    totalPoints = it?.totalPoints,
                    awayWins = it?.awayWins,
                    awayPointsConceded = it?.awayPointsConceded,
                    awayPointsScored = it?.awayPointsScored,
                    homeWins = it?.homeWins,
                    isQualified = it?.isQualified,
                    ga = it?.ga,
                    gf = it?.gf,
                    pointsPerMatch = it?.pointsPerMatch,
                    overallPointsPerMatch = it?.overallPointsPerMatch,
                    trumpMatchesWon = it?.trumpMatchesWon,
                    teamLogo = baseInfo.getTeamLogo(it?.teamId.toString())
                )
            }
        } ?: run {
            listOf()
        }
        return FootballStandingsData(
            teams = list
        )
    }

    private fun getListOfMatches(match: List<com.sportzinteractive.baseprojectsetup.data.model.footballstandings.Match?>?) =
        match?.takeLast(5)?.reversed()?.map {
            Match(
                date = CalendarUtils.convertDateStringToSpecifiedDateString(
                    it?.date ?: "",
                    dateFormat = CalendarUtils.MATCH_DATE_FORMAT,
                    requiredDateFormat = CalendarUtils.MATCH_REQUIRED_DATE_FORMAT
                ),
                id = it?.id,
                matchResult = it?.matchResult,
                postMatchPosition = it?.postMatchPosition,
                prevPosition = it?.prevPosition,
                result = it?.result,
                teamAId = it?.teamaId,
                teamAScore = it?.teamaScore,
                teamAShortName = it?.teamaShortName,
                teamAWinMargin = it?.teamaWinMargin,
                teamBId = it?.teambId,
                teamBScore = it?.teambScore,
                teamBShortName = it?.teambShortName,
                teamBWinMargin = it?.teambWinMargin,
                teamALogo = baseInfo.getTeamLogo(it?.teamaId.toString()),
                teamBLogo = baseInfo.getTeamLogo(it?.teambId.toString())
            )
        } ?: run {
            listOf()
        }

}