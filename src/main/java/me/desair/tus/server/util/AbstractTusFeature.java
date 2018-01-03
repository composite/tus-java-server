package me.desair.tus.server.util;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import me.desair.tus.server.HttpMethod;
import me.desair.tus.server.RequestHandler;
import me.desair.tus.server.RequestValidator;
import me.desair.tus.server.TusFeature;
import me.desair.tus.server.exception.TusException;
import me.desair.tus.server.upload.UploadStorageService;

public abstract class AbstractTusFeature implements TusFeature {

    private final List<RequestValidator> requestValidators = new LinkedList<>();
    private final List<RequestHandler> requestHandlers = new LinkedList<>();

    public AbstractTusFeature() {
        initValidators(requestValidators);
        initRequestHandlers(requestHandlers);
    }

    protected abstract void initValidators(final List<RequestValidator> requestValidators);

    protected abstract void initRequestHandlers(final List<RequestHandler> requestHandlers);

    @Override
    public void validate(final HttpMethod method, final HttpServletRequest servletRequest, final UploadStorageService uploadStorageService, final String ownerKey) throws TusException, IOException {
        for (RequestValidator requestValidator : requestValidators) {
            if(requestValidator.supports(method)) {
                requestValidator.validate(method, servletRequest, uploadStorageService, ownerKey);
            }
        }
    }

    @Override
    public void process(final HttpMethod method, final HttpServletRequest servletRequest, final TusServletResponse servletResponse, final UploadStorageService uploadStorageService, final String ownerKey) throws IOException, TusException {
        for (RequestHandler requestHandler : requestHandlers) {
            if(requestHandler.supports(method)) {
                requestHandler.process(method, servletRequest, servletResponse, uploadStorageService, ownerKey);
            }
        }
    }
}
