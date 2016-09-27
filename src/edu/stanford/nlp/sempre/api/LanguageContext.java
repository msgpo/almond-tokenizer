package edu.stanford.nlp.sempre.api;

import edu.stanford.nlp.sempre.*;
import edu.stanford.nlp.sempre.corenlp.CoreNLPAnalyzer;

class OnlineLearnEntry {
  private final String utterance;
  private final String targetJson;

  public OnlineLearnEntry(String utterance, String targetJson) {
    this.utterance = utterance;
    this.targetJson = targetJson;
  }

  @Override
  public String toString() {
    return utterance + "\t" + targetJson;
  }
}

class LanguageContext {
  public final String tag;
  public final Parser parser;
  public final Params params;
  public final LanguageAnalyzer analyzer;
  public final QueryCache cache = new QueryCache(256);
  public final Learner learner;
  public final ExactMatcherLayer exactMatch;

  public LanguageContext(String tag) {
    this(tag, new ExactMatcherLayer());
  }

  public LanguageContext(String tag, ExactMatcherLayer exactMatch) {
    this.tag = tag;
    this.exactMatch = exactMatch;

    Builder builder = new Builder();
    builder.buildForLanguage(tag);
    parser = builder.parser;
    params = builder.params;
    analyzer = new CoreNLPAnalyzer(tag);
    learner = new Learner(builder.parser, builder.params, null);
  }
}