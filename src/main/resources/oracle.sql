-- check should insert payload.
Select 'OWNDAT' as name from test.OWNDAT
Where paid = ? and taxer = ?
Union
Select 'OWNMLT' as name from test.OWNMLT
Where paid = ? and taxer = ?
