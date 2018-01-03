package me.desair.tus.server.core;

import javax.servlet.http.HttpServletRequest;

import me.desair.tus.server.HttpHeader;
import me.desair.tus.server.HttpMethod;
import me.desair.tus.server.RequestHandler;
import me.desair.tus.server.TusFileUploadService;
import me.desair.tus.server.upload.UploadStorageService;
import me.desair.tus.server.util.TusServletResponse;

/**
 * The Tus-Resumable header MUST be included in every request and response except for OPTIONS requests.
 * The value MUST be the version of the protocol used by the Client or the Server.
 */
public class CoreTusResumableHandler implements RequestHandler {

    @Override
    public boolean supports(final HttpMethod method) {
         return true;
    }

    @Override
    public void process(final HttpMethod method, final HttpServletRequest servletRequest, final TusServletResponse servletResponse, final UploadStorageService uploadStorageService, final String ownerKey) {
        //Always set Tus-Resumable header
        servletResponse.addHeader(HttpHeader.TUS_RESUMABLE, TusFileUploadService.TUS_API_VERSION);
    }
}
