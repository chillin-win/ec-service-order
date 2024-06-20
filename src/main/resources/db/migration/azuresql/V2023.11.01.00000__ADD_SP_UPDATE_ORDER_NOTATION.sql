-- CREATE OR ALTER PROCEDURE is not able to use for AzureSql
CREATE OR ALTER PROCEDURE sp_update_order_notation (
    @ai_id VARCHAR(255),
    @avc_notation VARCHAR(255) = 'Updated Notation From Store Procedure'
)
AS
BEGIN
    DECLARE @vc_errorMsg                   varchar(255),
            @i_errorCode                   int;

    BEGIN TRANSACTION;
        UPDATE orders
        SET notation = @avc_notation
        WHERE id = @ai_id;

    IF (@i_errorCode != 0)
        BEGIN
            ROLLBACK TRANSACTION;
        END;

    ELSE
        BEGIN
            COMMIT TRANSACTION;
        END;
END
GO
