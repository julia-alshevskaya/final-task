package com.alshevskaya.cleaningcompany.command;

import com.alshevskaya.cleaningcompany.command.admin.*;
import com.alshevskaya.cleaningcompany.command.cleaner.ShowOrdersByCleanerIdCommand;
import com.alshevskaya.cleaningcompany.command.cleaner.UpdatePersonalStaffDataCommand;
import com.alshevskaya.cleaningcompany.command.client.ShowOrdersByClientIdCommand;
import com.alshevskaya.cleaningcompany.command.client.UpdatePersonalDataCommand;
import com.alshevskaya.cleaningcompany.command.impl.ChangePasswordCommand;
import com.alshevskaya.cleaningcompany.command.impl.LoginCommand;
import com.alshevskaya.cleaningcompany.command.impl.LogoutCommand;
import com.alshevskaya.cleaningcompany.command.impl.SignUpCommand;
import com.alshevskaya.cleaningcompany.dao.impl.*;
import com.alshevskaya.cleaningcompany.service.serviceimpl.*;

public enum CommandType {
    LOGIN(new LoginCommand(new UserServiceImpl(new UserDaoImpl()))),
    LOGOUT(new LogoutCommand()),
    SIGNUP(new SignUpCommand(new ClientServiceImpl(new ClientDaoImpl()))),
    CHANGE_PASSWORD(new ChangePasswordCommand(new UserServiceImpl(new UserDaoImpl()))),
    SIGNUP_STAFF(new SignUpStaffCommand(new CleanerServiceImpl(new CleanerDaoImpl()))),
    SHOW_STAFF(new ShowStaffCommand(new CleanerServiceImpl(new CleanerDaoImpl()))),
    ADD_SERVICE(new AddServiceCommand(new CleaningServiceImpl(new ServiceDaoImpl()))),
    UPDATE_SERVICE(new UpdateServiceCommand(new CleaningServiceImpl(new ServiceDaoImpl()))),
    SHOW_SERVICES(new ShowServicesCommand(new CleaningServiceImpl(new ServiceDaoImpl()))),
    SHOW_ORDERS(new ShowOrdersCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    CHANGE_LOCALE(new ChangeLocaleCommand()),
    SHOW_CLIENTS(new ShowClientsCommand(new ClientServiceImpl(new ClientDaoImpl()))),
    ADD_ORDER(new AddOrderCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    DELETE_SERVICE(new DeleteServiceCommand(new CleaningServiceImpl(new ServiceDaoImpl()))),
    DELETE_ORDER(new DeleteOrderCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    UPDATE_ORDER(new UpdateOrderCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    UPDATE_STAFF_PERSONAL_DATA(new UpdatePersonalStaffDataCommand(new CleanerServiceImpl(new CleanerDaoImpl()))),
    SHOW_ORDERS_BY_CLIENT_ID(new ShowOrdersByClientIdCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    SHOW_ORDERS_BY_CLEANER_ID(new ShowOrdersByCleanerIdCommand(new OrderServiceImpl(new OrderDaoImpl()))),
    UPDATE_PERSONAL_DATA(new UpdatePersonalDataCommand(new ClientServiceImpl(new ClientDaoImpl())));

    CommandType(Command command) {
        this.command = command;
    }

    Command command;

    public Command getCurrentCommand() {
        return command;
    }
}
