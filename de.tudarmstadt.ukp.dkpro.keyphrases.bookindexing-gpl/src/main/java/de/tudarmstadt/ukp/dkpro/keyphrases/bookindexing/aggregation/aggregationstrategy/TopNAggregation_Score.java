/*******************************************************************************
 * Copyright 2013

 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl-3.0.txt
 ******************************************************************************/
package de.tudarmstadt.ukp.dkpro.keyphrases.bookindexing.aggregation.aggregationstrategy;

import java.util.*;
import java.util.Map.Entry;

import com.google.common.collect.Table;

/**
 * Every keyphrase in every segment gets a new score consisting of a base rank
 * and its normalized old score. In every segment the phrases are sorted
 * descending and get base ranks starting from a maximum rank and descending in
 * steps of 1. The normalized old score resolves the collision when two or more
 * phrases have the same base rank. Phrases with the same base rank and higher
 * old score are ranked higher then phrases with lower old score.
 *
 * @author Mateusz Parzonka
 *
 */
public class TopNAggregation_Score
	extends AggregationStrategy
{

	@Override
	public List<RankedPhrase> aggregatePhrases(
			Table<String, Integer, Double> phraseSegmentTable)
	{

		List<RankedPhrase> rankedPhrases = new ArrayList<RankedPhrase>();

		Map<Integer, Map<String, Double>> columnMap = phraseSegmentTable
				.columnMap();
		int maxRank = phraseSegmentTable.rowKeySet().size();
		double maxScore = maximum(phraseSegmentTable.values());

		for (Map<String, Double> column : columnMap.values()) {

			int baseRank = maxRank;
			for (Entry<String, Double> entry : sortByValue(column)) {

				double normalizedOldScore = entry.getValue() / maxScore;
				double newScore = baseRank-- + normalizedOldScore;

				rankedPhrases.add(new RankedPhrase(entry.getKey(), newScore));

			}
		}
		return rankedPhrases;

	}

	/**
	 * @return the number of entries to be retrieved
	 */
	protected int getN()
	{
		return 1;
	}

	/**
	 * Returns the top entries from the segment (modeled as column)
	 *
	 * @param column column
	 * @param n n
	 * @return the top entries
	 */
	protected final List<Entry<String, Double>> top(Map<String, Double> column,
			int n)
	{
		return sortByValue(column).subList(0, n);
	}

	private static <K, V> List<Entry<K, V>> sortByValue(Map<K, V> map)
	{
		List<Entry<K, V>> list = new LinkedList<Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<K, V>>()
		{
			@Override
			@SuppressWarnings({ "unchecked" })
			public int compare(Entry<K, V> o1, Entry<K, V> o2)
			{
				return -1 * ((Comparable<V>) o1.getValue()).compareTo(o2.getValue());
			}
		});
		return list;
	}

	private Double maximum(Collection<Double> scores)
	{
		double max = 0D;
		for (Double score : scores) {
			if (score > max)
				max = score;
		}
		return max;
	}
}