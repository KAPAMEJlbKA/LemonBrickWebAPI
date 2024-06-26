package pro.gravit.simplecabinet.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;
import pro.gravit.simplecabinet.web.model.user.User;
import pro.gravit.simplecabinet.web.model.user.UserSession;
import pro.gravit.simplecabinet.web.service.user.SessionService;
import pro.gravit.simplecabinet.web.service.user.UserDetailsService;
import pro.gravit.simplecabinet.web.service.user.UserService;

public class WithMockCabinetUserSecurityContextFactory implements WithSecurityContextFactory<WithCabinetUser> {
    @Autowired
    private UserDetailsService detailsService;
    @Autowired
    private SessionService sessionService;
    @Autowired
    private UserService userService;

    @Override
    public SecurityContext createSecurityContext(WithCabinetUser annotation) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        User user;
        if (annotation.userId() > 0) {
            user = userService.findById(annotation.userId()).orElseThrow();
        } else if (annotation.username() != null && !annotation.username().isEmpty()) {
            user = userService.findByUsername(annotation.username()).orElseThrow();
        } else {
            throw new SecurityException("User not found");
        }
        UserSession session = sessionService.create(user, "Test", "127.0.0.1");
        var details = detailsService.create(session);
        context.setAuthentication(new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities()));
        return context;
    }
}
