package de.tudarmstadt.ukp.dkpro.keyphrases.bookindexing.candidate.candidatesets;

import org.apache.uima.analysis_engine.AnalysisEngineDescription;
import org.apache.uima.fit.factory.AggregateBuilder;
import org.apache.uima.jcas.tcas.Annotation;
import org.apache.uima.resource.ResourceInitializationException;

import de.tudarmstadt.ukp.dkpro.core.api.segmentation.type.Token;
import de.tudarmstadt.ukp.dkpro.keyphrases.bookindexing.candidate.candidatesets.base.PosFilteredCandidateSet_BaseImpl;

/**
 * Creates an aggregate description used to produce a candidate annotation based
 * on tokens.
 *
 * @author Mateusz Parzonka
 *
 */
public class TokenCandidateSet
	extends PosFilteredCandidateSet_BaseImpl
{

	@Override
	public Class<? extends Annotation> getType()
	{
		return Token.class;
	}

	@Override
	public AnalysisEngineDescription createPreprocessingComponents(String language)
		throws ResourceInitializationException
	{
		AggregateBuilder builder = new AggregateBuilder();
		builder.add(getTokenizer(language));
		builder.add(getTagger(language));
		if (getPosToKeep().size() > 0) {
            builder.add(getPosFilter());
        }
		builder.add(getCandidateAnnotator());

		return builder.createAggregateDescription();
	}

	@Override
	public String toString()
	{
		return "CandidateType: Token" + LF + super.toString();
	}

}