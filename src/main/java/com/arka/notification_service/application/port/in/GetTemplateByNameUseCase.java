package com.arka.notification_service.application.port.in;

import com.arka.notification_service.application.dto.query.GetTemplateByNameQuery;
import com.arka.notification_service.domain.model.aggregate.Template;

public interface GetTemplateByNameUseCase {
    Template execute(GetTemplateByNameQuery query);
}
