QUnit.test( "hello test", function( assert ) {
    assert.ok( 1 == "1", "This is first test! Passed!" );
});

/*--------------------------------------------------------------------------------------------------------------------*/
QUnit.test( "get date test", function( assert ) {
    assert.equal(getDate(new Date(2010, 10, 10, 15, 20, 25)), "15:20:25 10.10.2010", "getdate is equals is expected!" );
});

QUnit.test( "get date test", function( assert ) {
    assert.equal(getDate(new Date(2010, 5, 7, 8, 4, 9)), "08:04:09 07.05.2010", "getdate is equals is expected!" );
});

/*--------------------------------------------------------------------------------------------------------------------*/
QUnit.test( "setNullInt test", function( assert ) {
    assert.equal(setNullInt(), "Error", "is Error" );
});

QUnit.test( "setNullInt test", function( assert ) {
    assert.equal(setNullInt(-5), "Error", "is Error" );
});

QUnit.test( "setNullInt test", function( assert ) {
    assert.equal(setNullInt(0), "00", "is Error");
});

QUnit.test( "setNullInt test", function( assert ) {
    assert.equal(setNullInt(10), "10", "True");
});

QUnit.test( "setNullInt test", function( assert ) {
    assert.equal(setNullInt(9), "09", "True");
});