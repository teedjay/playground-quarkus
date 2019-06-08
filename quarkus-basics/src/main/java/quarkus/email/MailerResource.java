package quarkus.email;

import io.quarkus.mailer.Attachment;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.stream.Collectors;

@Path("/mailer")
@Tag(name = "others")
@Produces(MediaType.APPLICATION_XML)
public class MailerResource {

    @Inject
    Mailer mailer;

    @GET
    @Operation(summary = "Sends some mails using the mailer extension")
    public MailerStatus sendCrazyMailWithRandomAttachments(@QueryParam("email") @NotNull @Email String email, @QueryParam("numberOfAttachments") @Min(0) @Max(5) int numberOfAttachments) {

        MailerStatus status = new MailerStatus();
        status.to = email;
        status.subject = "Crazy Mail To " + email;
        status.attachments = "some crazy attachment content goes here\n".repeat(numberOfAttachments).lines().collect(Collectors.toList());

        mailer.send(
            Mail.withText(status.to, status.subject, "This is the actual mail content")
                .setAttachments(status.attachments.stream()
                    .map(a -> new Attachment("file.txt", a.getBytes(), "text/plain"))
                        .collect(Collectors.toList())
                )
        );

        return status;

    }

}
