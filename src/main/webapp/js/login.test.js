//Unit Test 1 For Validating user
const{isValidUser} = require('./loginfunction.js');
test('should login with correcft details', () => {
    const resolve = isValidUser('12', '12');
    expect(resolve).toBe('Lo_AP_c-dlvdDyHQMHP')});

//Unit Test2 For Logging in with Correct Details


//Unit Test3 For Logging in with Incorrect Details

//UI Acceptance Test 1 For Logging In with Correct Details
test('should create an element with text and correct class', async () => {
    const browser = await puppeteer.launch({
        headless: true,
        // slowMo: 80,
        // args: ['--window-size=1920,1080']
    });
    const page = await browser.newPage();
    await page.goto(
        'file:///Users/mschwarzmueller/development/teaching/youtube/js-testing/index.html'
    );
    await page.click('input#name');
    await page.type('input#name', 'Anna');
    await page.click('input#age');
    await page.type('input#age', '28');
    await page.click('#btnAddUser');
    const finalText = await page.$eval('.user-item', el => el.textContent);
    expect(finalText).toBe('Anna (28 years old)');
}, 10000);

//UI Acceptance Test 1 For Forgetting Password
test('should create an element with text and correct class', async () => {
    const browser = await puppeteer.launch({
        headless: true,
        // slowMo: 80,
        // args: ['--window-size=1920,1080']
    });
    const page = await browser.newPage();
    await page.goto(
        'file:///Users/mschwarzmueller/development/teaching/youtube/js-testing/index.html'
    );
    await page.click('input#name');
    await page.type('input#name', 'Anna');
    await page.click('input#age');
    await page.type('input#age', '28');
    await page.click('#btnAddUser');
    const finalText = await page.$eval('.user-item', el => el.textContent);
    expect(finalText).toBe('Anna (28 years old)');
}, 10000);