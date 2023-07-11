package com.bafrow.account.domain;

import java.util.Optional;

public interface AccountsRepository {
  Optional<Account> authenticatedUserAccount();
}
