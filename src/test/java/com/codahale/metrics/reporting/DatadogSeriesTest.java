package com.codahale.metrics.reporting;

import com.codahale.metrics.reporting.model.DatadogSeries;
import static org.junit.Assert.*;
import org.junit.Test;

public class DatadogSeriesTest {

	public class TestSeries extends DatadogSeries<Long> {
		public TestSeries(String name, Long count, Long epoch, String host) {
			super(name, count, epoch, host);
		}

		public String getType() {
			return "test";
		}
	}

	@Test
	public void testParseMetricWithHyphens() {
		DatadogSeries series = new TestSeries("my.metric.with-some-hyphens", 0L, 0L, "");
		assertEquals("my.metric.with-some-hyphens", series.getMetric());
		assertEquals(0, series.getTags().size());
	}

	@Test
	public void testParseMetricWithHyphensAndTags() {
		DatadogSeries series = new TestSeries("my.metric.with-tags-and-hyphens[foo:bar]", 0L, 0L, "");
		assertEquals("my.metric.with-tags-and-hyphens", series.getMetric());
		assertEquals(1, series.getTags().size());
		assertEquals("foo:bar", series.getTags().get(0));
	}

}