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
package de.tudarmstadt.ukp.dkpro.keyphrases.bookindexing.evaluation.phrasematch;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import de.tudarmstadt.ukp.dkpro.core.api.metadata.type.DocumentMetaData;

/**
 * @author Mateusz Parzonka
 */
public class TokenReader implements StringReader
{

	private final String path;
	private final String suffix;
	private final String delimiter;
	private final boolean lowercase;

	/**
	 *
	 * @param path the path to the text file
	 * @param suffix the suffix
	 * @param delimiter the delimiter
	 * @param lowercase whether or not it should be lower case
	 */
	public TokenReader(String path, String suffix,String delimiter, boolean lowercase)
	{
		super();
		this.path = path;
		this.suffix = suffix;
		this.delimiter = delimiter;
		this.lowercase = lowercase;
	}

	@Override
	public Set<String> getSetOfStrings(final JCas jcas)
		throws AnalysisEngineProcessException
	{
		return new HashSet<String>(getListOfStrings(jcas));
	}

	@Override
	public List<String> getListOfStrings(final JCas jcas)
		throws AnalysisEngineProcessException
	{
		String goldPhrases;
		try {
			goldPhrases = FileUtils.readFileToString(new File(FilenameUtils.concat(path, getDocumentBaseName(jcas) + normalizeSuffix(suffix))));
		}
		catch (final IOException e) {
			throw new AnalysisEngineProcessException(new Throwable(e));
		}

		final List<String> result = new ArrayList<String>();

		for (String goldPhrase : goldPhrases.split(delimiter)) {
			goldPhrase = lowercase ? goldPhrase.toLowerCase() : goldPhrase;
			goldPhrase = goldPhrase.trim();
			if (!goldPhrase.isEmpty())
				result.add(goldPhrase);
		}

		return result;
	}

	/**
	 * @param jcas the jcas
	 * @return the document basename from the parsed document-URI-path.
	 * @throws AnalysisEngineProcessException An analysis engine processing exception
	 */
	private String getDocumentBaseName(JCas jcas)
		throws AnalysisEngineProcessException
	{
		try {
			URI uri = new URI(DocumentMetaData.get(jcas).getDocumentUri());
			return FilenameUtils.getBaseName(uri.getPath());
		}
		catch (URISyntaxException e) {
			throw new AnalysisEngineProcessException(e);
		}
	}

	private static String normalizeSuffix(String suffix) {
		return suffix.startsWith(".") ? suffix : "." + suffix;
	}

}
