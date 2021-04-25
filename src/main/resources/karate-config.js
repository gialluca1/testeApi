function fn() {
  var env = java.lang.System.getProperty('TEST_ENV');

   var config = {
        BaseURL:'https://jsonplaceholder.typicode.com',
        EnvName:'',
        currentTime:''
    };


  karate.configure('retry',{ count:5, interval:5000});
  karate.log('the environment is ' + config.EnvName);
  karate.configure('logPrettyResponse', true);
  karate.configure('connectTimeout', 60000);
  karate.configure('readTimeout', 60000);
  return config;
}