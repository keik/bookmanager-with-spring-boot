'use strict';

module.exports = function(grunt) {

  require('load-grunt-tasks')(grunt);
  require('time-grunt')(grunt);

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),


    // env

    dir: {
      templates: 'src/main/resources/templates/',
      assets: 'src/main/resources/static/'
    },

    watch: {
      options: {
        livereload: true,
        spawn: true
      },

      'jshint-gruntfile': {
        files: '<%= jshint.gruntfile.src %>',
        tasks: ['jshint:gruntfile']
      },

      'jshint-js': {
        files: '<%= jshint.js.src %>',
        tasks: ['jshint:js']
      },

      less: {
        files: ['<%= dir.assets %>less/**/*.less'],
        tasks: ['recess:lint', 'recess:compile']
      }

    },

    bower: {
      options: {
        layout: 'byComponent',
        install: true,
        cleanup: true
      },
      bower: {
        options: {
          targetDir: '<%= dir.assets %>vendor/',
          production: false
        }
      }
    },

    connect: {
      options: {
        port: 9000,
        hostname: '0.0.0.0',
        base: ['src/main/resources']
      },
      dev: {
        options: {
          middleware: function (connect, options) {

            var middlewares = [];

            if (!Array.isArray(options.base))
              options.base = [options.base];

            options.base.forEach(function (base) {
              middlewares.push(connect.static(base));
            });

            var directory = options.directory || options.base[options.base.length - 1];
            middlewares.push(connect.directory(directory));

            return middlewares;
          }
        }
      }
    },

    modernizr: {

      modernizr: {
        devFile: 'node_modules/grunt-modernizr/lib/modernizr-dev.js',
        outputFile: '<%= bower.bower.options.targetDir %>modernizr/modernizr.js',
        extra : {
          shiv : true,
          printshiv : false,
          load : true,
          mq : true,
          cssclasses : true
        },
        uglify : false,
        tests : ['touch'],
        parseFiles : true,
        files : {
          src: ['<%= dir.assets %>js/**/*.js', '<%= dir.assets %>css/**/*.css']
        }
      }
    },


    // js

    jshint: {
      options: {
        jshintrc: '.jshintrc',
        reporter: require('jshint-stylish')
      },
      gruntfile: {
        src: ['Gruntfile.js' ]
      },
      js: {
        src: ['<%= dir.assets %>js/**/*.js']
      }
    },


    // css

    recess: {
      options: {
        noOverqualifying: false,
        noUniversalSelectors: false
      },

      lint: {
        src: ['<%= dir.assets %>less/main.less']
      },

      compile: {
        options: {
          compile: true
        },
        files: [
          {
            '<%= dir.assets %>css/style.css': [
              '<%= dir.assets %>less/main.less',
              '<%= dir.assets %>less/icomoon.less'
            ]
          }
        ]
      }
    },


    // dist

    clean: {
      dev: ['<%= dir.assets %>css/', '<%= dir.assets %>vendor/']
    }

  });

  grunt.registerTask('default', ['connect:dev', 'watch']);
  grunt.registerTask('install', ['bower', 'recess', 'modernizr']);
};
