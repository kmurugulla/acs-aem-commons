package com.adobe.acs.commons.twitter;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import junitx.util.PrivateAccessor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

@RunWith(MockitoJUnitRunner.class)
public class TwitterOAuthCommunicatorTest {

	private TwitterOAuthCommunicator twitterOAuthCommunicator;

	@Mock
	private Logger LOGGER;

	@Mock
	private TwitterConfiguration twitterConfiguration;

	@Mock
	private Twitter twitter;

	@Mock
	private ResponseList<Status> responseList;

	@Before
	public void setUp() throws Exception {
		twitterOAuthCommunicator = new TwitterOAuthCommunicator();

		PrivateAccessor.setField(twitterOAuthCommunicator, "twitter", twitter);
		PrivateAccessor.setField(twitterOAuthCommunicator, "LOGGER", LOGGER);

		when(twitter.getUserTimeline(anyString())).thenReturn(responseList);

	}

	@Test
	public void testDefaultInstanceBehaviour() throws Exception {

		twitterOAuthCommunicator = new TwitterOAuthCommunicator();

		TwitterFactory twitterFactory = (TwitterFactory) PrivateAccessor.getField(twitterOAuthCommunicator, "twitterFactory");
		assertNotNull(twitterFactory);
	}

	@Test
	@Ignore
	public void test_WhenGetTweetsAsListIsInvoked_ThenCallsGetUserTimeline() throws Exception {
		
		twitterOAuthCommunicator.getTweetsAsList(twitterConfiguration);

		verify(twitter).getOAuth2Token();
		verify(twitter).getUserTimeline(anyString());

		verify(twitterConfiguration).getConsumerKey();
		verify(twitterConfiguration).getConsumerSecret();
		verify(twitterConfiguration).getUsername();
	}

}
