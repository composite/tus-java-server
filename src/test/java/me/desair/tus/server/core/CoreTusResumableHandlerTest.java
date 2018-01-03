package me.desair.tus.server.core;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import me.desair.tus.server.HttpHeader;
import me.desair.tus.server.HttpMethod;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.util.TusServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class CoreTusResumableHandlerTest {

    private MockHttpServletRequest servletRequest;

    private MockHttpServletResponse servletResponse;

    private CoreTusResumableHandler handler;

    @Before
    public void setUp() {
        servletRequest = new MockHttpServletRequest();
        servletResponse = new MockHttpServletResponse();
        handler = new CoreTusResumableHandler();
    }

    @Test
    public void supports() throws Exception {
        assertThat(handler.supports(HttpMethod.GET), is(true));
        assertThat(handler.supports(HttpMethod.POST), is(true));
        assertThat(handler.supports(HttpMethod.PUT), is(true));
        assertThat(handler.supports(HttpMethod.DELETE), is(true));
        assertThat(handler.supports(HttpMethod.HEAD), is(true));
        assertThat(handler.supports(HttpMethod.OPTIONS), is(true));
        assertThat(handler.supports(HttpMethod.PATCH), is(true));
        assertThat(handler.supports(null), is(true));
    }

    @Test
    public void process() throws Exception {
        handler.process(HttpMethod.PATCH, servletRequest, new TusServletResponse(servletResponse), null, null);

        assertThat(servletResponse.getHeader(HttpHeader.TUS_RESUMABLE), is(TusFileUploadService.TUS_API_VERSION));
    }
}