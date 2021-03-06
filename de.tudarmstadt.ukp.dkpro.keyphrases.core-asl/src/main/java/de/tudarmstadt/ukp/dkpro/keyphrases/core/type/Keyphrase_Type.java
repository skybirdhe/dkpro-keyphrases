/*******************************************************************************
 * Copyright 2013
 * Ubiquitous Knowledge Processing (UKP) Lab
 * Technische Universität Darmstadt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.tudarmstadt.ukp.dkpro.keyphrases.core.type;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Thu Apr 18 17:45:18 CEST 2013
 * */
public class Keyphrase_Type extends Annotation_Type {
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (Keyphrase_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = Keyphrase_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new Keyphrase(addr, Keyphrase_Type.this);
  			   Keyphrase_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new Keyphrase(addr, Keyphrase_Type.this);
  	  }
    };
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = Keyphrase.typeIndexID;

  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase");
 
  final Feature casFeat_score;
 
  final int     casFeatCode_score;
  /**
   * @param addr the memory address
   * @return the keyphrase score
   *  */ 
  public double getScore(int addr) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase");
    return ll_cas.ll_getDoubleValue(addr, casFeatCode_score);
  }
  /** 
   * @param addr the memory address
   * @param v the score value
   * */    
  public void setScore(int addr, double v) {
        if (featOkTst && casFeat_score == null)
      jcas.throwFeatMissing("score", "de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase");
    ll_cas.ll_setDoubleValue(addr, casFeatCode_score, v);}
    
  
 
  final Feature casFeat_keyphrase;
  final int     casFeatCode_keyphrase;
  
  /**
   * @param addr the memory address
   * @return The keyphrase set
   * */ 
  public String getKeyphrase(int addr) {
        if (featOkTst && casFeat_keyphrase == null)
      jcas.throwFeatMissing("keyphrase", "de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase");
    return ll_cas.ll_getStringValue(addr, casFeatCode_keyphrase);
  }
  /** 
   * @param addr the memory address
   * @param v the keyphrase value to be set
   * */    
  public void setKeyphrase(int addr, String v) {
        if (featOkTst && casFeat_keyphrase == null)
      jcas.throwFeatMissing("keyphrase", "de.tudarmstadt.ukp.dkpro.keyphrases.core.type.Keyphrase");
    ll_cas.ll_setStringValue(addr, casFeatCode_keyphrase, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	* @param jcas The jcas which the type will be annotated
	* @param casType the type itself
	* */
  public Keyphrase_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_score = jcas.getRequiredFeatureDE(casType, "score", "uima.cas.Double", featOkTst);
    casFeatCode_score  = (null == casFeat_score) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_score).getCode();

 
    casFeat_keyphrase = jcas.getRequiredFeatureDE(casType, "keyphrase", "uima.cas.String", featOkTst);
    casFeatCode_keyphrase  = (null == casFeat_keyphrase) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_keyphrase).getCode();

  }
}



    