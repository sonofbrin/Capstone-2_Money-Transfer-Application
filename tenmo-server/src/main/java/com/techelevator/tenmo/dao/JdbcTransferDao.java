package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;
    //private Logger log = LoggerFactory.getLogger(getClass());

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Transfer> list() {
        return null;
    }

    @Override
    public List<Transfer> list(Principal principal) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "Select transfer_id As 'Id', tenmo_user.username As 'username', amount From transfer " +
                "Join account On transfer.account_from = account.account_id " +
                "Join tenmo_user On account.account_id = tenmo_user.user_id" +
                "Where tenmo_user.user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, principal.getName());
        while (results.next()){
            Transfer allTransfers = mapRowToTransfer(results);
            transfers.add(allTransfers);
        }
        return transfers;
    }


    private Transfer mapRowToTransfer(SqlRowSet srs){
        Transfer t = new Transfer();
        t.setTransferId(srs.getLong("transfer_id"));
        t.setTransferAmount(srs.getInt("amount"));
        t.setAccountFromId(srs.getLong("account_from"));
        t.setAccountToId(srs.getLong("account_to"));
        t.setTransferStatusId(srs.getInt("transfer_status_id"));
        t.setTransferTypeId(srs.getInt("transfer_type_id"));
        return t;
    }
}
