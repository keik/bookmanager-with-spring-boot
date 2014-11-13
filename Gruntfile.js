'use strict';

module.exports = function(grunt) {

  require('load-grunt-tasks')(grunt);
  require('time-grunt')(grunt);

  grunt.initConfig({
    pkg: grunt.file.readJSON('package.json'),


    // env

    dir: {
      src: 'src/main/resources/work/',
      dist: 'src/main/resources/templates/',
      docs: 'src/main/resources/docs/',
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
        files: ['<%= dir.src %>less/**/*.less'],
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
          targetDir: '<%= dir.src %>vendor/',
          production: false
        }
      }
    },

    connect: {
      options: {
        port: 9000,
        hostname: '0.0.0.0',
        base: ['src']
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
          src: ['<%= dir.src %>js/**/*.js', '<%= dir.src %>css/**/*.css']
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
        src: ['<%= dir.src %><%= dir.assets %>js/**/*.js']
      }
    },


    // css

    recess: {
      options: {
        noOverqualifying: false
      },

      lint: {
        src: ['<%= dir.src %>less/main.less']
      },

      compile: {
        options: {
          compile: true
        },
        files: [
          {
            '<%= dir.src %>css/style.css': [
              '<%= dir.src %>less/main.less',
              '<%= dir.src %>less/icomoon.less'
            ]
          }
        ]
      }
    },


    // dist

    clean: {
      dev: ['<%= dir.src %>css/', '<%= dir.src %>vendor/'],
      dist: ['<%= dir.dist %>', '<%= dir.assets %>'],
      docs: ['<%= dir.docs %>']
    },

    htmlmin: {
      dist: {
        options: {
          removeComments: false,
          collapseWhitespace: false
        },
        expand: true,
        cwd: '<%= dir.dist %>',
        src: ['**/*.html'],
        dest: '<%= dir.dist %>'
      }
    },

    cssmin: {
      dist: {
        expand: true,
        cwd: '<%= dir.assets %>',
        src: ['**/*.css'],
        dest: '<%= dir.assets %>'
      }
    },

    uglify: {
      options: {
        preserveComments: 'some'
      },
      dist: {
        expand: true,
        cwd: '<%= dir.src %>',
        src: ['**/*.js'],
        dest: '<%= dir.assets %>'
      }
    },

    copy: {
      html: {
        expand: true,
        cwd: '<%= dir.src %>',
        src: ['**/**.html'],
        dest: '<%= dir.dist %>'
      },
      assets: {
        expand: true,
        cwd: '<%= dir.src %>',
        src: ['css/**', 'img/**', 'fonts/**', 'js/**', 'json/**'],
        dest: '<%= dir.assets %>'
      }
    },

    jsdoc: {
      dist: {
        src: ['<%= dir.src %>js/**/*.js'],
        dest: '<%= dir.docs %>js'
      }
    }

  });

  grunt.registerTask('default', ['connect:dev', 'watch']);
  grunt.registerTask('install', ['bower', 'recess', 'modernizr'/*, 'jsdoc'*/]);
  grunt.registerTask('build', ['clean', 'bower', 'recess', 'modernizr', 'copy:html', 'copy:assets'/*, 'htmlmin'*/, 'cssmin', 'uglify'/*, 'jsdoc'*/]);

  // build test
  grunt.registerTask('test', ['install', 'build']);

};
