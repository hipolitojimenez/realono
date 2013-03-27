package com.nioos.realono.bdd;



import java.util.ArrayList;
import java.util.List;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.Format;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;



@RunWith(JUnitReportingRunner.class)
public class RealONoBehaviorTest extends JUnitStories {
	
	
	private List<String> storyPaths = new ArrayList<String>();
	
	
	private List<Object> stepsInstances = new ArrayList<Object>();
	
	
	public RealONoBehaviorTest() {
		storyPaths.add("com/nioos/realono/bdd/real_o_no.story");
		stepsInstances.add(new RealONoSteps());
		//
		Configuration configuration = getConf();
		InjectableStepsFactory stepsFactory =
			new InstanceStepsFactory(configuration, stepsInstances);
		useStepsFactory(stepsFactory);
	}
	
	
	private Configuration getConf() {
		Configuration conf = configuration();
		StoryReporterBuilder storyReporterBuilder =
			conf.storyReporterBuilder();
		storyReporterBuilder.withFormats(Format.CONSOLE, Format.HTML,
			Format.TXT);
		conf.useStoryReporterBuilder(storyReporterBuilder);
		conf.usePendingStepStrategy(new FailingUponPendingStep());
		return conf;
	}
	
	
	@Override
	protected List<String> storyPaths() {
		return storyPaths;
	}
	
	
}
