package site.aloui.cqrsandeventsourcing.query.queries;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class GetAccountStatementQuery {

    private String accountId;
}
