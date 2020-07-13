package framework.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class ExceptionEnumConstants {
    @Getter
    @AllArgsConstructor
    public enum ApiExceptionDescription {
        NO_AVAILABLE_DATES_FOR_RESCHEDULE_IN_SCHEDULED_SERVICE_CALENDAR("No available dates for reschedule in Scheduled Service Calendar"),
        NONE_OF_AVAILABLE_UNIT_ITEM_LINE_HAS_AVAILABLE_RESCHEDULE_DATES_EXPECTED_DATES_TO_RESCHEDULE("None of available unit item line has available reschedule dates. Expected dates to reschedule '%s'"),
        NOT_CONFIRMED_UNIT_IS_NOT_FOUND_ON_CURRENT_TAB_ON_MAINTENANCE_PAGE("Reschedulable Unit is not found on current tab on Maintenance page"),
        THERE_IS_NO_UNIT_ITEM_LINE_WITH_DATES_FOR_RESCHEDULE_MORE_THAN_EXPECTED_IN_RANGE("There is no unit item line with dates for reschedule more than expected in range '%s'"),
        THERE_IS_NO_UNIT_LINE_ITEM_WITH_AVAILABLE_DATES_FOR_RESCHEDULE_BY_CONDITION_FOUND("There is no UnitLineItem with available dates for reschedule by '%s' condition found"),
        THERE_IS_NO_UNIT_LINE_ITEM_WITH_AVAILABLE_DATES_FOR_RESCHEDULE_FOUND("There is no UnitLineItem with available dates for reschedule found"),
        THERE_IS_NO_UNIT_LINE_ITEM_WITH_NOT_AVAILABLE_DATES_FOR_RESCHEDULE_FOUND("There is no UnitLineItem with not available dates for reschedule found"),
        UNIT_WITH_REPAIR_ORDER_IS_PM_IS_NOT_FOUND_ON_CURRENT_TAB_ON_MAINTENANCE_PAGE("Unit with 'repairOrderIsPm' is not found on current tab on Maintenance page");
        private String errorDescription;

        @Override
        public String toString() {
            return errorDescription;
        }
    }
}
