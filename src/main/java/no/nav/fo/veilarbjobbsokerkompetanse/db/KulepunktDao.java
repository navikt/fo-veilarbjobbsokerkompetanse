package no.nav.fo.veilarbjobbsokerkompetanse.db;

import lombok.SneakyThrows;
import no.nav.fo.veilarbjobbsokerkompetanse.domain.Kulepunkt;
import no.nav.sbl.jdbc.Database;

import java.sql.ResultSet;
import java.util.List;

class KulepunktDao {

    private final Database db;

    KulepunktDao(Database db) {
        this.db = db;
    }

    void create(Kulepunkt kulepunkt, long kartleggingId) {
        long kulepunktId = db.nesteFraSekvens("KULEPUNKT_SEQ");
        db.update("INSERT INTO KULEPUNKT (" +
                "kulepunkt_id, " +
                "kartlegging_id, " +
                "kulepunkt_key, " +
                "kulepunkt_prioritet, " +
                "kulepunkt) " +
                "VALUES (?, ?, ?, ?, ?)",
            kulepunktId,
            kartleggingId,
            kulepunkt.getKulepunktKey(),
            kulepunkt.getKulepunktPrioritet(),
            kulepunkt.getKulepunkt()
        );
    }

    List<Kulepunkt> fetchByKartleggingId(long kartleggingId) {
        return db.query("SELECT * FROM KULEPUNKT WHERE kartlegging_id = ?",
            this::map,
            kartleggingId
        );
    }

    @SneakyThrows
    private Kulepunkt map(ResultSet rs) {
        return Kulepunkt.builder()
            .kulepunktId(rs.getLong("kulepunkt_id"))
            .kartleggingId(rs.getLong("kartlegging_id"))
            .kulepunktKey(rs.getString("kulepunkt_key"))
            .kulepunktPrioritet(rs.getInt("kulepunkt_prioritet"))
            .kulepunkt(rs.getString("kulepunkt"))
            .build();
    }
}
